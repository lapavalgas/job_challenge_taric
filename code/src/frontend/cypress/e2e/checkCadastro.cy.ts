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
        cy.contains("h5", "Cadastro de novo cliente");
        cy.wait(200);
    });
})

describe("Check the form validation", () => {
    it("Click in the save button", () => {
        cy.get("a").contains('Cadastrar novo cliente ').click();
        cy.wait(200);
    });
})

describe("Check the form masks ", () => {
    it("Check the CPF", () => {
        cy.get("#cpf").type('00000000000').blur();
        cy.get("#cpf").invoke('val')
            .then((val) => {
                cy.log("PASS: " + (val === '000.000.000-00'))
            })
        cy.wait(200);
    });
    it("Check the Tel", () => {
        cy.get("#telefone").type('000000000000').blur();
        cy.get("#telefone").invoke('val')
            .then((val) => {
                cy.log("PASS: " + (val === '(00) 00000-0000'))
            })
        cy.wait(200);
    });
    it("Check the CEP", () => {
        cy.get("#cep").type('88101175').blur();
        cy.get("#cep").invoke('val')
            .then((val) => {
                cy.log("PASS: " + (val === '88101-175'))
            })
        cy.wait(1000);
    });
})

describe("Type customer fake data", () => {
    it("Check the CPF", () => {
        cy.get("#nomeCompleto").type('Pedrinho Alvares').blur();
        cy.wait(200);
    });
    it("Check the Tel", () => {
        cy.get("#email").type('teste@teste.com').blur();
        cy.wait(200);
    });
    it("Check the CEP", () => {
        cy.get("#numero").type('1').blur();
        cy.wait(1000);
    });
    it("Check the CEP", () => {
        cy.get("#complemento").type('Caravelas').blur();
        cy.wait(1000);
    });
})

describe("Check the save button in the register customer feature", () => {
    it("Click in the save button", () => {
        cy.wait(200);
        cy.get("button").contains("Cadastrar").click();
    });
    it("Click in the ok button", () => {
        cy.wait(301);
        cy.on('window:alert', (text) => {
            cy.wait(200);
            expect(text).to.contains('Deseja salvar os dados do cliente?');
        });
    });
})

describe("Exit app", () => {
    it("Click in the logout button", () => {
        cy.wait(200);
        cy.get("a").contains('LOGOUT').click();
    });
});