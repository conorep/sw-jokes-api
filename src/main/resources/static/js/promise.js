//creating a promise that divides the two numbers
let promise = new Promise(function(resolve, reject) {
    //inside of the executor stored in the promise
    let num = prompt("Enter a numerator");
    let denominator = prompt("Enter a denominator");

    //convert to numbers
    num = parseFloat(num);
    denominator = parseFloat(denominator);

    //check for bad inputs (NaN)
    if(Number.isNaN(num) || Number.isNaN(denominator))
    {
        throw Error("User entered non-numerical value.");
    }

    if(denominator === 0)
    {
        reject("Cannot divide by zero.");
    } else
    {
        let result = num/denominator;

        //report success using the first parameter (which is a function)
        resolve("Result is " + result);
    }

});

//first function: resolve. second: reject

//NOTE: can't use failure AND error. one or other.
promise
    .then(function(success) {
        console.log("The promise was successful.");
        console.log(success);
    }, function(failure) {
        console.log("The promise was unsuccessful.");
        console.log(failure);
    })
    .catch((function(error) {
        console.log("something went wrong.");
        console.log(error);
    }));