import { useState } from 'react';
import axios from 'axios';
import { mensagemSucesso,mensagemErro,mensagemAlert } from '../components/toastr';
import { useParams } from 'react-router-dom';

const useFetch = () => {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const {id} =useParams();

    const get = async (url, successCallback) => {
        setLoading(true);
        setError(null);

        try {
            const response = await axios.get(url);
            setLoading(false);
            mensagemSucesso("Logado com sucesso!")
            successCallback(response.data);
        } catch (error) {
            setLoading(false);
            setError(error);
            mensagemErro(error.message);
        }
    };

    const post = async (url, data) => {
        setLoading(true);
        setError(null);

        try {
            const response = await axios.post(url,data);
            setLoading(false);
            mensagemSucesso(response.data);
        } catch (error) {
            setLoading(false);
            mensagemErro(error.data);
        }
    };


    const put = async (url, data)=>{
        setLoading(true);
        setError(null)
        try {
            const response = await axios.put(url,data)
            mensagemSucesso(response.data)
            setLoading(false)
        } catch (error) {
            setLoading(false);
            mensagemAlert(error.response.data.message);
        }
    }

    const del = async (url,data) =>{
        try {
        const response = await axios.delete(url,data);
        mensagemSucesso(response.data)
        setLoading(false)
        } catch (error) {
            setLoading(false);
            mensagemAlert(error.response.data.message);
        }
    }

    return { loading, error, get, post,put,del };
};

export default useFetch;
