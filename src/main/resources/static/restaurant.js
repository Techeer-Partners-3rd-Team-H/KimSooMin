// 등록 기능
const createButton = document.getElementById('create-btn');

if (createButton) {
    createButton.addEventListener("click", event => {
        fetch("/api/restaurants", {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                restaurantNme: document.getElementById("restaurant-name").value,
                category: document.getElementById('category').value,
            })
        })
        .then(() => {
            alert('등록이 완료되었습니다.');
            location.replace("/restaurants");
        });
    });
}

// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('restaurant-id').value;
        fetch('/api/restaurants/' + id, {
            method: 'DELETE'
        })
        .then(() => {
            alert("삭제가 완료되었습니다.");
            location.replace('/restaurants');
        });
    });
}

// 수정 기능
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('restaurant-id');

        fetch('/api/restaurants/' + id, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                // restaurantName: document.getElementById('restaurant-name').value,
                category: document.getElementById('category').value,
            })
        })
        .then(() => {
            alert("수정이 완료되었습니다.");
            location.replace('/restaurants/' + id);
        });
    });
}