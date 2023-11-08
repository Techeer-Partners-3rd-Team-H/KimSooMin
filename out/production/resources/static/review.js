// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('review-id').value;
        fetch('/api/reviews/' + id, {
            method: 'DELETE'
        })
        .then(() => {
            alert("삭제가 완료되었습니다.");
            location.replace('/reviews');
        });
    });
}

// 수정 기능
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch('/api/reviews/' + id, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                restaurant: document.getElementById('restaurant').value,
                content: document.getElementById('content').value
            })
        })
        .then(() => {
            alert("수정이 완료되었습니다.");
            location.replace('/reviews/' + id);
        });
    });
}

// 등록 기능
const createButton = document.getElementById('create-btn');

if (createButton) {
    createButton.addEventListener("click", event => {
        fetch("/api/reviews", {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                restaurant: document.getElementById('restaurant').value,
                content: document.getElementById("content").value
            })
        })
        .then(() => {
            alert('등록이 완료되었습니다.');
            location.replace("/reviews");
        });
    });
}
// 검색 기능
const searchButton = document.getElementById('search-button');
const searchInput = document.getElementById('search-input');
const reviewsContainers = document.querySelectorAll('.row-6');

if (searchButton) {
    searchButton.addEventListener('click', () => {
        const searchValue = searchInput.value;
        if (searchValue) {
            // 모든 카드 컨테이너 삭제
            reviewsContainers.forEach(container => {
                container.innerHTML = '';
            });

            fetch(`/api/reviews/search?keyword=${searchValue}`)
                .then(response => response.json())
                .then(reviews => {
                    if (reviews.length === 0) {
                        reviewsContainer.innerHTML = '검색 결과가 없습니다.';
                    } else {
                        reviews.forEach(review => {
                            // 새로운 카드 생성
                            const card = document.createElement('div');
                            card.className = 'card';

                            // 카드 내용 (생략)
                            const cardHeader = document.createElement('div');
                            cardHeader.className = 'card-header';
                            cardHeader.textContent = review.title;
                            card.appendChild(cardHeader);

                            const cardBody = document.createElement('div');
                            cardBody.className = 'card-body';

                            const titleElement = document.createElement('h5');
                            titleElement.className = 'card-title';
                            titleElement.textContent = review.title;
                            cardBody.appendChild(titleElement);

                            const restaurantElement = document.createElement('h6');
                            restaurantElement.className = 'card-title';
                            restaurantElement.textContent = review.restaurant;
                            cardBody.appendChild(restaurantElement);

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
                }
            })
            .catch(error => {
                console.error('검색 중 오류 발생:', error);
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
        .then(reviews => {
            // 서버에서 반환된 정렬된 게시글을 표시
            reviewsContainers.forEach(container => {
                container.innerHTML = '';
            });
            reviews.forEach(review => {
                // 새로운 카드 생성
                const card = document.createElement('div');
                card.className = 'card';

                // 카드 내용 (생략)
                const cardHeader = document.createElement('div');
                cardHeader.className = 'card-header';
                cardHeader.textContent = review.title;
                card.appendChild(cardHeader);

                const cardBody = document.createElement('div');
                cardBody.className = 'card-body';

                const titleElement = document.createElement('h5');
                titleElement.className = 'card-title';
                titleElement.textContent = review.title;
                cardBody.appendChild(titleElement);

                const restaurantElement = document.createElement('h6');
                restaurantElement.className = 'card-title';
                restaurantElement.textContent = review.restaurant;
                cardBody.appendChild(restaurantElement);

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
