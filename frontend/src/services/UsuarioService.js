import axios from "axios";

const BASE_URL_USUARIO = "http://localhost:5353/api/finance"

export const createUsuario = (nome,senha,email,role) => axios.post(BASE_URL_USUARIO + "/salvar",{nome,senha,email,role})

export const deleteUsuario = (idUsuario) => axios.delete(BASE_URL_USUARIO,idUsuario)

export const updateUsuario = (idUsuario,usuario) => axios.put(BASE_URL_USUARIO,{idUsuario,usuario})
