const pressEnter = "{enter}";
const backspaceOpenCep = '{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}';


describe("Open app and do the authentication", () => {
    it("Open app", () => {
        sessionStorage.setItem("isUserLoggedValue", "undefined");
        cy.visit("/");
        cy.wait(200);
    });
    it("Type the right credentials", () => {
        cy.get("#userLoginInput").type("admin");
        cy.get("#passLoginInput").type("admin");
        cy.get("#btn-login").click();
        cy.wait(400);
    });
});

describe("Navigate to address feature", () => {
    it("Click in the addresses button", () => {
        cy.get("a").contains('Endereços').click();
        cy.wait(200);
    });
})

describe("Check the address search feature with valid data", () => {
    it("Type a valid ZIP code input", () => {
        cy.wait(200);
        cy.get("input").type("88101175").blur();
        cy.wait(200);
        cy.get("a").contains("Buscar CEP").click();
        cy.wait(1000);
    });

    it("Check the result element for valid data", () => {
        cy.get(".logradouro").contains('Avenida Acioni Souza Filho');
        cy.wait(200);
    });
})

describe("Check the address search feature with invalid data", () => {
    it("Type a invalid ZIP code input", () => {
        cy.wait(200);
        cy.get("input").type(backspaceOpenCep).blur();
        cy.wait(200);
        cy.get("input").type("00111010").blur();
        cy.wait(200);
        cy.get("a").contains("Buscar CEP").click();
        cy.wait(1000);
    });

    it("Check the result element for invalid data", () => {
        cy.wait(1000);
        cy.get(".logradouro").contains('404');
        cy.wait(200);
    });
})

describe("Check the address search feature with enter key", () => {
    it("Type any data in ZIP code input", () => {
        cy.get("input").type("88803140" + pressEnter);
        cy.wait(1000);
    });

    it("Check the result element for key press", () => {
        cy.get("div .card").should('be.visible');
        cy.wait(200);
    });
})