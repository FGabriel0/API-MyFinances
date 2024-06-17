import React, { useState } from 'react'
import Card from '../components/Card'
import FormGroup from '../components/FormGroup'
import { mensagemErro, mensagemSucesso } from '../components/toastr'

const Cadastra = () => {
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [senhaRepetida, setSenhaRepetida] = useState("");
    const [message, setMessage] = useState('');



    const handleSubmit = async (e) => {
        e.preventDefault();

    }

  return (
    <Card title="Cadastro de Usuário">
    <div className="row">
        <div className="col-lg-12">
            <div className="bs-component">
                <FormGroup label="Nome: *" htmlFor="inputNome">
                    <input type="text" 
                           id="inputNome" 
                           className="form-control"
                           name="nome"
                           value={nome}
                           onChange={(e) => setNome(e.target.value)} />
                </FormGroup>
                <FormGroup label="Email: *" htmlFor="inputEmail">
                    <input type="email" 
                           id="inputEmail"
                           className="form-control"
                           name="email"
                           value={email}
                           onChange={(e) => setEmail(e.target.value)} />
                </FormGroup>
                <FormGroup label="Senha: *" htmlFor="inputSenha">
                    <input type="password" 
                           id="inputSenha"
                           className="form-control"
                           name="senha"
                           value={senha}
                           onChange={(e) => setSenha(e.target.value)} />
                </FormGroup>
                <FormGroup label="Repita a Senha: *" htmlFor="inputRepitaSenha">
                    <input type="password" 
                           id="inputRepitaSenha"
                           className="form-control"
                           name="senha"
                           value={senhaRepetida}
                           onChange={(e) => setSenhaRepetida(e.target.value)} />
                </FormGroup>
                <button onClick={handleSubmit} type="button" className="btn btn-success">
                    <i className="pi pi-save"></i> Salvar
                </button>
                <button  type="button" className="btn btn-danger">
                    <i className="pi pi-times"></i> Cancelar
                </button>
            </div>
        </div>
    </div>
</Card>
  )
}

export default Cadastra