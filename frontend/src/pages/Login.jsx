import React, { useState} from "react";
import Card from "../components/Card";
import FormGroup from "../components/FormGroup";
import { loginAPICall,saveLoggedInUser,storeToken } from "../services/AuthService";
import { useNavigate } from "react-router-dom";
import { mensagemErro, mensagemSucesso } from "../components/toastr";

const Login = () => {
  const [nome, setNome] = useState("");
  const [senha, setSenha] = useState("");
  const navigate = useNavigate();

  async function handleLoginForm(e){

    e.preventDefault();
    await loginAPICall(nome, senha).then((response) => {
        console.log(response.data);

        const token = response.data.token;
        storeToken(token);
        mensagemSucesso("Logado com Sucesso");
        saveLoggedInUser(nome);
        navigate("/home")

        window.location.reload(false);
    }).catch(error => {
        console.error(error);
    })

}
  const prepareCadastrar = () => {
    navigate("/cadastra");
  };

  return (
    <div className="row" style={{ height: '90vh' }}>
      <div className="col-md-6 offset-md-3">
        <div className="bs-docs-section">
          <Card title="Login">
            <div className="row">
              <div className="col-lg-12">
                <div className="bs-component">
                  <fieldset>
                    <FormGroup label="Nome: *" htmlFor="Usuario">
                      <input
                        type="text"
                        value={nome}
                        onChange={(e) => setNome(e.target.value)}
                        className="form-control"
                        id="exampleInputEmail1"
                        aria-describedby="emailHelp"
                        placeholder="Digite seu Usuario"
                      />
                    </FormGroup>
                    <FormGroup label="Senha: *" htmlFor="exampleInputPassword1">
                      <input
                        type="password"
                        value={senha}
                        onChange={(e) => setSenha(e.target.value)}
                        className="form-control"
                        id="exampleInputPassword1"
                        placeholder="Password"
                      />
                    </FormGroup>
                    <button onClick={handleLoginForm} className="btn btn-success">
                      <i className="pi pi-sign-in"></i> Entrar
                    </button>
                    <button
                      onClick={prepareCadastrar}
                      className="btn btn-danger"
                    >
                      <i className="pi pi-plus"></i> Cadastrar
                    </button>
                  </fieldset>
                </div>
              </div>
            </div>
          </Card>
        </div>
      </div>
    </div>
  );
};

export default Login;
