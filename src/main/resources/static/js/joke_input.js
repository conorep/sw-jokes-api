const submitBtn = document.getElementById("joke-submit-btn");
submitBtn.addEventListener('click', clickSubmitJoke, false);

function clickSubmitJoke(event)
{
    event.preventDefault();

    let textVal = document.getElementById("joke-input").value;

    let jsonObject = {
        jokeText : textVal
    }

    document.getElementById("joke-input").value = "";

    let url = "http://localhost:8080/api/v1/joke";

    let params = {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(jsonObject)
    }

    fetch(url, params)
        .then(response => console.log(response));
}

