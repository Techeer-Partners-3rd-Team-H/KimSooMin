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
        .then(response => {
            // 여기에 응답 코드 확인
            console.log('응답 코드:', response.status);

            // 응답 코드에 따른 처리
            if (response.ok) {
                alert('등록이 완료되었습니다.');
                location.replace("/reviews");
            } else {
                alert('등록에 실패했습니다. 응답 코드: ' + response.status);
            }
        })
        .catch(error => {
            console.error('등록 오류:', error);
            alert('등록 중 오류가 발생했습니다.');
        });
    });
}

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
