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

describe("Navigate to register customer feature", () => {
    it("Click in the register customer button", () => {
        cy.get("a").contains('Cadastrar novo cliente').click();
        cy.wait(200);
    });
})

describe("Check the OpenCEP communications", () => {
    it("Type a valid ZIP code", () => {
        cy.get("#cep").type('88101175').blur();
        cy.wait(1000);
    });
})

describe("Check the results", () => {
    it("One by one", () => {
        cy.get("#logradouro").invoke('val')
            .then((val) => {
                cy.log("PASS: " + (val === 'Avenida Acioni Souza Filho'))
            })
        cy.wait(100);
        cy.get("#bairro").invoke('val')
            .then((val) => {
                cy.log("PASS: " + (val === 'Campinas'))
            })
        cy.wait(100);
        cy.get("#cidade").invoke('val')
            .then((val) => {
                cy.log("PASS: " + (val === 'São José'))
            })
        cy.wait(100);
        cy.get("#estado").invoke('val')
            .then((val) => {
                cy.log("PASS: " + (val === 'SC'))
            })
        cy.wait(100);
    });
})

describe("Exit app", () => {
    it("Click in the logout button", () => {
        cy.wait(200);
        cy.get("a").contains('LOGOUT').click();
    });
});
