// 검색 기능
const searchButton = document.getElementById('search-button');
const searchInput = document.getElementById('search-input');
const reviewsContainers = document.querySelectorAll('.row-6');

if (searchButton) {
    searchButton.addEventListener('click', () => {
        const searchValue = searchInput.value;
        if (searchValue) {
            // 모든 카드 컨테이너 비우기
            reviewsContainers.forEach(container => {
                container.innerHTML = '';
            });

            fetch(`/api/reviews/search?keyword=${searchValue}`)
                .then(response => response.json())
                .then(reviews => {
                    const cardContainer = document.createElement('div'); // 새로운 컨테이너 생성
                    reviewsContainers.forEach(container => {
                        // 리뷰를 표시할 하나의 컨테이너에만 추가
                        container.appendChild(cardContainer);
                    });

                    if (reviews.length === 0) {
                        cardContainer.innerHTML = '검색 결과가 없습니다.';
                    } else {
                        reviews.forEach(review => {
                            // 새로운 카드 생성
                            const card = document.createElement('div');
                            card.className = 'card';

                            const cardHeader = document.createElement('div');
                            cardHeader.className = 'card-header';
                            cardHeader.textContent = review.restaurant.restaurantName;
                            card.appendChild(cardHeader);

                            const cardBody = document.createElement('div');
                            cardBody.className = 'card-body';

                            const titleElement = document.createElement('h6');
                            titleElement.className = 'card-title';
                            titleElement.textContent = review.title;
                            cardBody.appendChild(titleElement);

                            const contentElement = document.createElement('p');
                            contentElement.className = 'card-text';
                            contentElement.textContent = review.content;
                            cardBody.appendChild(contentElement);

                            // "보러가기" 버튼 추가
                            const viewButton = document.createElement('a');
                            viewButton.href = '/reviews/' + review.id;
                            viewButton.className = 'btn btn-primary';
                            viewButton.textContent = '보러가기';
                            cardBody.appendChild(viewButton);

                            card.appendChild(cardBody);
                            cardContainer.appendChild(card); // 컨테이너에 카드 추가
                        });
                    }
                });
        }
    });
}


// 게시글 정렬
const sortByRecentButton = document.getElementById('sortByRecent');
const sortByOldestButton = document.getElementById('sortByOldest');

sortByRecentButton.addEventListener('click', () => {
    sortReviews('recent'); // 최신순 정렬 옵션을 전달
});

sortByOldestButton.addEventListener('click', () => {
    sortReviews('oldest'); // 오래된순 정렬 옵션을 전달
});

function sortReviews(sortOption) {
    fetch(`/api/reviews?sort=${sortOption}`)
        .then(response => response.json())
        .then(page => {
            const reviews = page.content;
            reviewsContainers.forEach(container => {
                container.innerHTML = '';
            });
            reviews.forEach(review => {
                // 새로운 카드 생성
                const card = document.createElement('div');
                card.className = 'card';

                const cardHeader = document.createElement('div');
                cardHeader.className = 'card-header';
                cardHeader.textContent = review.restaurant.restaurantName;
                card.appendChild(cardHeader);

                const cardBody = document.createElement('div');
                cardBody.className = 'card-body';

                const titleElement = document.createElement('h6');
                titleElement.className = 'card-title';
                titleElement.textContent = review.title;
                cardBody.appendChild(titleElement);

                const contentElement = document.createElement('p');
                contentElement.className = 'card-text';
                contentElement.textContent = review.content;
                cardBody.appendChild(contentElement);

                // "보러가기" 버튼 추가
                const viewButton = document.createElement('a');
                viewButton.href = '/reviews/' + review.id;
                viewButton.className = 'btn btn-primary';
                viewButton.textContent = '보러가기';
                cardBody.appendChild(viewButton);

                card.appendChild(cardBody);

                reviewsContainers.forEach(container => {
                    container.appendChild(card);
                });
            });
        })
        .catch(error => {
            console.error('게시글 정렬 중 오류 발생:', error);
        });
}
