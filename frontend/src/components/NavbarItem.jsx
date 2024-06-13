import React from 'react'

const NavbarItem = ({onClick,label,href}) => {
        return (
            <li className="nav-item">
                <a onClick={onClick} className="nav-link" href={href}>{label}</a>
            </li>
        )

}

export default NavbarItem