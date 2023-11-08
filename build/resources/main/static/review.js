// 삭제 기능
const deleteButton = document.getElementById('delete-btn')

if (deleteButton){
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('review-id').value;
        fetch('/api/reviews/'+id, {
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

if (modifyButton){
    modifyButton.addEventListener('click', event =>{
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch('/api/reviews/'+id, {
            method: 'PUT',
            headers:{
                "Content-Type":"application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                restaurant: document.getElementById('restaurant').value,
                content: document.getElementById('content').value
            })
        })
        .then(() => {
            alert("수정이 완료되었습니다.");
            location.replace('/reviews/'+id);
        });
    });
}

// 등록 기능
const createButton = document.getElementById('create-btn');

if (createButton){
    createButton.addEventListener("click", event => {

        fetch("/api/reviews", {
            method: 'POST',
            headers: {
                "Content-Type":"application/json",
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
const titleInput = document.getElementById('title-input');
const contentInput = document.getElementById('content-input');

if (searchButton) {
    searchButton.addEventListener('click', () => {
            const titleValue = titleInput.value;
            const contentValue = contentInput.value;
        if (searchInput) {
            fetch(`/api/reviews/search?title=${titleValue}&content=${contentValue}`)
                .then(response => response.json())
                .then(reviews => {
                    // 검색 결과를 사용하여 원하는 방식으로 페이지 업데이트 또는 출력합니다.
                    // 예: 검색 결과를 리스트로 표시하거나 상세 정보를 출력합니다.
                    const searchResultsContainer = document.getElementById('search-results');
                    searchResultsContainer.innerHTML = ''; // 기존 내용을 지웁니다.

                    if (reviews.length === 0) {
                        searchResultsContainer.innerHTML = '검색 결과가 없습니다.';
                    } else {
                        reviews.forEach(review => {
                            const reviewItem = document.createElement('div');

                            // 제목
                            const titleElement = document.createElement('h3');
                            titleElement.textContent = review.title;
                            reviewItem.appendChild(titleElement);

                            // 레스토랑
                            const restaurantElement = document.createElement('h5');
                            restaurantElement.textContent = review.restaurant;
                            reviewItem.appendChild(restaurantElement);

                            // 내용
                            const contentElement = document.createElement('p');
                            contentElement.textContent = review.content;
                            reviewItem.appendChild(contentElement);

                            searchResultsContainer.appendChild(reviewItem);
                        });
                    }
                })
                .catch(error => {
                    console.error('검색 중 오류 발생:', error);
                });
            }
        });
    }
