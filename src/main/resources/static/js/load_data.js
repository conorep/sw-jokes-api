//wait until page loads
window.onload = function() {
    //create objects to make a get request

    let uri = "http://localhost:8080/api/v1/joke";
    let params = {
        method: "GET",
        mode: "cors",
        headers: {
            "Content-Type": "application/json"
        }
    }

    fetch(uri, params)
        .then(function(response)
        {
            return response.json();
        })
        .then(function(data)
        {
            showJokes(data);
        });
};

function showJokes(data)
{
    //access list in our HTML
    let jokesList = document.getElementById("jokes-list");

    //create a bunch of new sections
    /*
        <section>
            <h2>joke + ....</h2>
            <p>Han and Leia...</>

        </section
     */
    for (let i = 0; i < data.length; i++)
    {
        let joke = data[i];

        let jokeSection = document.createElement("section");
        let jokeH2 = document.createElement("h2");
        let jokeParagraph = document.createElement("p");


        //create new list item for each joke
        jokeH2.innerText = "Joke ID# " + joke.id;
        jokeParagraph.innerText = joke.jokeText;


        //add the list item to the list
        jokesList.appendChild(jokeSection);
        jokeSection.appendChild(jokeH2);
        jokeSection.appendChild(document.createElement("hr"));
        jokeSection.appendChild(jokeParagraph);
    }

}
