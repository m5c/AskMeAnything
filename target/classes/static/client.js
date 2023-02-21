function registerButtons() {

    // Register click handlers
    console.log("Registering poll submit button");
    document.getElementById("submitbutton").addEventListener("click", submitPoll)

    // focus on input filed by default
    document.getElementById("question").focus();
}



async function submitPoll() {

    // Get values of input fields:
    let question = document.getElementById("question").value;
    console.log(question);

    if (!question) {
        alert("Question must not be empty!")
        return;
    }

    // Deactivate button to prevent further clicks
    document.getElementById("submitbutton").disabled = true;


    fetch('http://85.214.243.137:8361/ama/question', {
        method: 'post',
        headers: {
            'Content-Type': 'application/text; charset=utf-8'
        },
        body: question
    }).then(reply => reply.text())
        .then(textreply => {
            console.log(textreply)
        });
}