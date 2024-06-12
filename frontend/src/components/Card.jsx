import React from 'react'

const Card = ({children,title}) => {
  return (
    <div className="card md-3">
                <h3 className="card-header">{title}</h3>
                <div className="card-body">
                    {children}
                </div>
            </div>
  )
}

export default Card