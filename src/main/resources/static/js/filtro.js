window.addEventListener("load", () => {
    const filtro = document.querySelector("#filtro");
    const result = document.querySelector(".result");
    const formEdit = document.querySelector("#formEdit");

    filtro.addEventListener("change", (event) => {
        formEdit.action = `/busca${event.target.value}`;
    });

});