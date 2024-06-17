import React, { useEffect, useState } from 'react'
import NavBar from '../components/NavBar'
import { jwtDecode } from 'jwt-decode'
import { obterSaldo } from '../services/UsuarioService'

const Home = () => {
const [saldos,setSaldo] = useState(0)

useEffect(() => {
  const token = localStorage.getItem("token");
  const decodeToken = jwtDecode(token);
  const usuario_id = decodeToken.userId;

  obterSaldo(usuario_id).then(response => {
    setSaldo(response.data.data.saldo)
  }).catch(error=>{
    console.error("Erro ao obter saldo:", error.response);
  })
    
}, []);


  return (
    <div className="jumbotron">
    <NavBar/>
    <h1 className="display-3">Bem vindo!</h1>
    <p className="lead">Esse é seu sistema de finanças.</p>
    <p className="lead">Seu saldo para o mês atual é de R$ {saldos} </p>
    <hr className="my-4" />
    <p>E essa é sua área administrativa, utilize um dos menus ou botões abaixo para navegar pelo sistema.</p>
    <p className="lead">
        <a className="btn btn-primary btn-lg" 
        href="/cadastro-usuarios" 
        role="button"><i className="pi pi-users"></i>  
         Cadastrar Usuário
        </a>
        <a className="btn btn-danger btn-lg" 
        href="/cadastra-lancamentos" 
        role="button"><i className="pi pi-money-bill"></i>  
         Cadastrar Lançamento
        </a>
    </p>
</div>

  )
}

export default Home