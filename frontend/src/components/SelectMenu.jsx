import React from 'react'

const SelectMenu = (props) => {
    const { lista, ...rest } = props; // Extrai a propriedade lista do props

    const options = lista.map((option, index) => (
        <option key={index} value={option.value}>
            {option.label}
        </option>
    ));

    return (
        <select {...rest}>
            {options}
        </select>
    );
};

export default SelectMenu