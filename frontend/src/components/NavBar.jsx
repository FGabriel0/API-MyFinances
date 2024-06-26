import React from 'react'
import { logout } from '../services/AuthService'
import NavbarItem from './NavbarItem'

const NavBar = () => {
  return (
    <div className="navbar navbar-expand-lg fixed-top navbar-dark bg-primary">
        <div className="container">
          <a href="/home" className="navbar-brand">Minhas Finanças</a>
          <button className="navbar-toggler" type="button" 
                  data-toggle="collapse" data-target="#navbarResponsive" 
                  aria-controls="navbarResponsive" aria-expanded="false" 
                  aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarResponsive">
            <ul className="navbar-nav">
                <NavbarItem  href="/home" label="Home" />
                <NavbarItem  href="/cadastro-usuarios" label="Usuários" />
                <NavbarItem  href="/consultar-lancamentos" label="Lançamentos" />
                <NavbarItem  onClick={logout} href="/" label="Sair" />
            </ul>
            </div>
        </div>
      </div>
  )
}

export default NavBar