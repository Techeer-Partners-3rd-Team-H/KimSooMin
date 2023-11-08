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
