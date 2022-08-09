describe("Open app", () => {
  it("Open app", () => {
    sessionStorage.setItem("isUserLoggedValue", "undefined");
    cy.visit("/");
    cy.wait(200);
    cy.contains("h5", "Sign in");
  });
});

describe("Check authentication login failed", () => {
  it("Type the wrong credentials", () => {
    cy.visit("/");
    cy.get("#userLoginInput").type("taric1234");
    cy.get("#passLoginInput").type("taric1234");
    cy.get("#btn-login").click();
    cy.wait(200);
  });
});

describe("Check authentication login successful", () => {
  it("Type the right credentials", () => {
    cy.visit("/");
    cy.get("#userLoginInput").type("taric");
    cy.get("#passLoginInput").type("taric1234");
    cy.get("#btn-login").click();
    cy.wait(400);
    cy.contains("h5", "Clientes");
  });
})

describe("Check authentication sessionStorage", () => {
  it("Manually remove sessionStorage auth and try navigate the app", () => {
    sessionStorage.setItem("isUserLoggedValue", "undefined");
    cy.visit("/clientes");
    cy.wait(200);
    cy.contains("h5", "Sign in");
  });
})

describe("ReCheck authentication login successful", () => {
  it("Type the right credentials", () => {
    cy.contains("h5", "Sign in");
    cy.visit("/");
    cy.get("#userLoginInput").type("taric");
    cy.get("#passLoginInput").type("taric1234");
    cy.get("#btn-login").click();
    cy.wait(400);
    cy.contains("h5", "Clientes");
  });
})

describe("Check authentication logout", () => {
  it("Click in the logout button", () => {
    cy.get("a").contains('LOGOUT').click();
    cy.wait(400);
    cy.contains("h5", "Sign in");
  });
});

describe("ReCheck authentication login failed", () => {
  it("Type the wrong credentials", () => {
    cy.contains("h5", "Sign in");
    cy.visit("/");
    cy.get("#userLoginInput").type("taric1234");
    cy.get("#passLoginInput").type("taric1234");
    cy.get("#btn-login").click();
    cy.wait(400);
  });
});