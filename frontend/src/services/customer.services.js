import axios from "axios";

const BASE_URL = "http://localhost:1200/api/v1/customer";

export const addCustomer = async (customer) => {
    try {
        const response = await axios.post(`${BASE_URL}/addCustomer`, customer);
        return response.data;
    } catch (error) {
        console.error(
            "Error adding customer:",
            error.response ? error.response.data : error.message
        );
        throw error;
    }
};

export const findById = async (id) => {
    try {
        const response = await axios.get(`${BASE_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error(
            "Error fetching customer:",
            error.response ? error.response.data : error.message
        );
        throw error;
    }
};

export const addAdmin = async (admin) => {
    try {
        const response = await axios.post(`${BASE_URL}/addAdmin`, admin);
        return response.data;
    } catch (error) {
        console.error(
            "Error adding admin:",
            error.response ? error.response.data : error.message
        );
        throw error;
    }
};

export const getByEmail = async (email) => {
    try {
        const response = await axios.get(`${BASE_URL}/email/${email}`);
        return response.data;
    } catch (error) {
        console.error(
            "Error fetching customer by email:",
            error.response ? error.response.data : error.message
        );
        throw error;
    }
};
