import axios from "axios";

const BASE_URL_LANCAMENTO = "http://localhost:5353/api/lancamento"

export const salvarLancamento = (objectLancamento, token) => axios.post(BASE_URL_LANCAMENTO + "/salvar", objectLancamento, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
});

export const listaLancamento = (usuario_id, token) => axios.get(BASE_URL_LANCAMENTO + "/" + usuario_id, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})

export const updateLancamento = (id, lancamentos, token) => axios.put(BASE_URL_LANCAMENTO + `/${id}`, lancamentos, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})

export const deleteLancamento = (id, token) => axios.delete(BASE_URL_LANCAMENTO + `/${id}`, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})

export const updateStatus = (id,novoStatus) => axios.patch(BASE_URL_LANCAMENTO + `/${id}`,novoStatus,{
    headers: {
        'Authorization': `Bearer ${token}`
    }
})


