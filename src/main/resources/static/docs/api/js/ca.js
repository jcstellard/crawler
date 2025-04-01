let currentPage = 1;
const pageSize = 5;

function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

const sendParam = getQueryParam('send') || '';

async function fetchData(page) {
    try {
        const loadingDiv = document.getElementById('loading');
        loadingDiv.style.display = 'block';

        const apiUrl = `/api/getCaByPage?page=${page}&pageSize=${pageSize}&send=${encodeURIComponent(sendParam)}`;
        const response = await fetch(apiUrl);

        if (!response.ok) {
            throw new Error('error');
        }

        const result = await response.json();
        renderTable(result.data);
        renderPagination(result.total, page);
    } catch (error) {
        console.error('get data err:', error);
        alert('get data err');
    } finally {
        const loadingDiv = document.getElementById('loading');
        loadingDiv.style.display = 'none';
    }
}

function renderTable(data) {
    const tableBody = document.querySelector('#dataTable tbody');
    tableBody.innerHTML = '';

    data.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${item.ca}</td>
            <td>${item.create_time}</td>
            <td>${item.urls}</td>
        `;
        tableBody.appendChild(row);
    });
}

function renderPagination(total, currentPage) {
    const paginationDiv = document.getElementById('pagination');
    paginationDiv.innerHTML = '';

    const totalPages = Math.ceil(total / pageSize);

    const prevButton = document.createElement('button');
    prevButton.innerText = 'previous page';
    prevButton.disabled = currentPage === 1;
    prevButton.addEventListener('click', () => {
        if (currentPage > 1) {
            currentPage--;
            updateUrl(currentPage);
            fetchData(currentPage);
        }
    });
    paginationDiv.appendChild(prevButton);

    const maxVisiblePages = 5;
    let startPage = Math.max(1, currentPage - Math.floor(maxVisiblePages / 2));
    let endPage = Math.min(totalPages, startPage + maxVisiblePages - 1);

    if (endPage - startPage < maxVisiblePages - 1) {
        startPage = Math.max(1, endPage - maxVisiblePages + 1);
    }

    if (startPage > 1) {
        const firstPageButton = document.createElement('button');
        firstPageButton.innerText = '1';
        firstPageButton.addEventListener('click', () => {
            currentPage = 1;
            updateUrl(currentPage);
            fetchData(currentPage);
        });
        paginationDiv.appendChild(firstPageButton);

        if (startPage > 2) {
            const ellipsis = document.createElement('span');
            ellipsis.innerText = '...';
            paginationDiv.appendChild(ellipsis);
        }
    }

    for (let i = startPage; i <= endPage; i++) {
        const pageButton = document.createElement('button');
        pageButton.innerText = i;
        if (i === currentPage) {
            pageButton.classList.add('active');
        } else {
            pageButton.classList.remove('active');
        }
        pageButton.addEventListener('click', () => {
            currentPage = i;
            updateUrl(currentPage);
            fetchData(currentPage);
        });
        paginationDiv.appendChild(pageButton);
    }

    if (endPage < totalPages) {
        if (endPage < totalPages - 1) {
            const ellipsis = document.createElement('span');
            ellipsis.innerText = '...';
            paginationDiv.appendChild(ellipsis);
        }

        const lastPageButton = document.createElement('button');
        lastPageButton.innerText = totalPages;
        lastPageButton.addEventListener('click', () => {
            currentPage = totalPages;
            updateUrl(currentPage);
            fetchData(currentPage);
        });
        paginationDiv.appendChild(lastPageButton);
    }

    const nextButton = document.createElement('button');
    nextButton.innerText = 'next page';
    nextButton.disabled = currentPage === totalPages;
    nextButton.addEventListener('click', () => {
        if (currentPage < totalPages) {
            currentPage++;
            updateUrl(currentPage);
            fetchData(currentPage);
        }
    });
    paginationDiv.appendChild(nextButton);
}

function updateUrl(page) {
    const url = new URL(window.location);
    url.searchParams.set('page', page);
    url.searchParams.set('send', sendParam);
    window.history.pushState({}, '', url);
}

function getPageFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return parseInt(urlParams.get('page')) || 1;
}

document.addEventListener('DOMContentLoaded', () => {
    currentPage = getPageFromUrl();
    fetchData(currentPage);
});
