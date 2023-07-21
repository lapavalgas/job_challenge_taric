const backspace = '{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}{backspace}';

const leftArrow = "{leftArrow}"


describe("Open app and do the authentication", () => {
    it("Open app", () => {
        sessionStorage.setItem("isUserLoggedValue", "undefined");
        cy.visit("/");
    });
    it("Type the right credentials", () => {
        cy.wait(400);
        cy.get("#userLoginInput").type("admin");
        cy.get("#passLoginInput").type("admin");
        cy.get("#btn-login").click();
    });
});

describe("Navigate to customer feature", () => {
    it("Click in the customer button", () => {
        cy.wait(400);
        cy.get("a").contains('Clientes').click();
        cy.wait(200);
        cy.contains("h5", "Clientes");
    });
})

describe("Check the customer-details componenet", () => {
    it("Click in the first customer", () => {
        cy.wait(200);
        cy.get("span button").first().click();
        cy.wait(1000);
    });

    it("Click in the edit button", () => {
        cy.wait(200);
        cy.get("a").contains("Editar").click();
    });
})

describe("Check the edit customer componenet", () => {
    it("Check the title", () => {
        cy.wait(200);
        cy.get("h5").contains("Editando os dados do cliente");
    });

    it("Change values to name", () => {
        cy.wait(200);
        cy.get("#nomeCompleto").type(backspace)
        cy.wait(200);
        cy.get("#nomeCompleto").type('Pedro Alvares Cabral').blur();
        cy.wait(200);
    });

    it("Change values to email", () => {
        cy.wait(200);
        cy.get("#email").type(backspace)
        cy.wait(200);
        cy.get("#email").type('teste@teste.com').blur();
        cy.wait(200);
    });

    it("Change values to telefone", () => {
        cy.get("#telefone").type(backspace)
        cy.wait(200);
        cy.get("#telefone").type(leftArrow)
        cy.wait(10);
        cy.get("#telefone").type(backspace)
        cy.wait(200);
        cy.get("#telefone").type('00000000000').blur();
        cy.wait(200);
    });

    it("Change values to cep", () => {
        cy.get("#cep").type(backspace)
        cy.wait(200);
        cy.get("#cep").type(leftArrow)
        cy.wait(10);
        cy.get("#cep").type(backspace)
        cy.wait(200);
        cy.get("#cep").type('88101175').blur();
        cy.wait(2000);
    });

    it("Change values to numero", () => {
        cy.get("#numero").type(backspace)
        cy.wait(200);
        cy.get("#numero").type('1').blur();
        cy.wait(400);
    });
    
    it("Change values to complemento", () => {
        cy.wait(200);
        cy.get("#complemento").type(backspace)
        cy.wait(200);
        cy.get("#complemento").type('Na casinha de madeira, sem número').blur();
        cy.wait(200);
    });
})

describe("Check save feature", () => {
    it("Click in the save button", () => {
        cy.get("div button").contains('Salvar').click();
        cy.wait(300);
        cy.on('window:alert', (text) => {
            cy.wait(200);
            expect(text).to.contains('Deseja salvar os dados do cliente?');
            cy.wait(200);
        });
    });
})

describe("Check the save feature succesful", () => {
    it("Check the data changed", () => {
        cy.wait(200);
        cy.get("span button").contains('Pedro Alvares Cabral')
        cy.wait(200);
    });
})

describe("Check the delete button in the edit feature", () => {
    it("Click in the first customer", () => {
        cy.wait(201);
        cy.get("span button").first().click();
        cy.wait(1000);
    });

    it("Click in the edit button", () => {
        cy.wait(200);
        cy.get("a").contains("Editar").click();
    });
})

describe("Check the delete button", () => {
    it("Check the delete button in edit area", () => {
        cy.wait(200);
        cy.get("button").last().contains("Deletar").click();
    });
    it("Click in the delete button", () => {
        cy.wait(300);
        cy.on('window:alert', (text) => {
            cy.wait(200);
            expect(text).to.contains('Deseja deletar os dados do cliente?');
        });
    });
})

describe("Check the delete button in the customer list feature", () => {
    it("Click in the first customer", () => {
        cy.wait(202);
        cy.get("span button").first().click();
        cy.wait(1000);
    });
    it("Click in the edit button", () => {
        cy.wait(200);
        cy.get("button").contains("Deletar").click();
    });
    it("Click in the delete button", () => {
        cy.wait(301);
        cy.on('window:alert', (text) => {
            cy.wait(200);
            expect(text).to.contains('Deseja deletar os dados do cliente?');
        });
    });
})

describe("Exit app", () => {
    it("Click in the logout button", () => {
        cy.wait(200);
        cy.get("a").contains('LOGOUT').click();
    });
});