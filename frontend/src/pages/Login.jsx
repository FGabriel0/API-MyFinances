import React, { useState, useContext, useEffect } from "react";
import Card from "../components/Card";
import FormGroup from "../components/FormGroup";
import { useNavigate } from "react-router-dom";
import axios from "axios";

import { mensagemErro, mensagemSucesso } from "../components/toastr";
import useFetch from "../hooks/useFetch";

const Login = () => {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [message, setMessage] = useState('');

  const navigate = useNavigate();
  const {loading,error,post} = useFetch();

  
  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      post('http://localhost:5353/api/finance', {
          email:email,
          senha:senha
      });
      navigate("/home")
  } catch (error) {
      console.error('Erro ao cadastrar usuário', error);
      mensagemErro('Erro ao cadastrar usuário.');
  }
};


  const prepareCadastrar = () => {
    navigate("/cadastra");
  };

  return (
    <div className="row align-items-center" style={{ height: '90vh' }}>
      <div className="col-md-6 offset-md-3">
        <div className="bs-docs-section">
          <Card title="Login">
            <div className="row">
              <div className="col-lg-12">
                <div className="bs-component">
                  <fieldset>
                    <FormGroup label="Email: *" htmlFor="exampleInputEmail1">
                      <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="form-control"
                        id="exampleInputEmail1"
                        aria-describedby="emailHelp"
                        placeholder="Digite o Email"
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
                    <button onClick={handleLogin} className="btn btn-success">
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
