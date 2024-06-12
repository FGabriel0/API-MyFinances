import React from 'react'

const FormGroup = ({htmlFor,label,children}) => {
  return (
    <div className="form-group">
    <label htmlFor={htmlFor}>{label}</label>
    {children}
</div>
  )
}

export default FormGroup