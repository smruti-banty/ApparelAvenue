// product.services.js
import axios from "axios";

const BASE_URL = "http://localhost:1200/api/v1/products"; // Base URL for product-related API

export const getProductById = async (productId) => {
  try {
    const response = await axios.get(`${BASE_URL}/${productId}`);
    return response.data;
  } catch (error) {
    console.error(
      "Error fetching product:",
      error.response ? error.response.data : error.message
    );
    throw error;
  }
};

export const deleteProductById = async (id) => {
  try {
    const response = await axios.delete(`${BASE_URL}/delete/${id}`);
    return response.data;
  } catch (error) {
    console.error(
      "Error deleting product:",
      error.response ? error.response.data : error.message
    );
    throw error;
  }
};

export const createProduct = async (productRequestDto) => {
  try {
    const response = await axios.post(BASE_URL, productRequestDto);
    return response.data;
  } catch (error) {
    console.error(
      "Error creating product:",
      error.response ? error.response.data : error.message
    );
    throw error;
  }
};

export const updateProduct = async (productId, productUpdateRequestDto) => {
  try {
    const response = await axios.put(
      `${BASE_URL}/${productId}`,
      productUpdateRequestDto
    );
    return response.data;
  } catch (error) {
    console.error(
      "Error updating product:",
      error.response ? error.response.data : error.message
    );
    throw error;
  }
};

export const updateProductPrice = async (id, price) => {
  try {
    const response = await axios.patch(
      `${BASE_URL}/${id}/updatePrice/${price}`
    );
    return response.data;
  } catch (error) {
    console.error(
      "Error updating product price:",
      error.response ? error.response.data : error.message
    );
    throw error;
  }
};

export const deleteAllProducts = async () => {
  try {
    const response = await axios.delete(`${BASE_URL}/all`);
    return response.data;
  } catch (error) {
    console.error(
      "Error deleting all products:",
      error.response ? error.response.data : error.message
    );
    throw error;
  }
};

export const decreaseProductQuantity = async (id, quantity) => {
  try {
    const response = await axios.patch(
      `${BASE_URL}/${id}/decrement/${quantity}`
    );
    return response.data;
  } catch (error) {
    console.error(
      "Error decreasing product quantity:",
      error.response ? error.response.data : error.message
    );
    throw error;
  }
};
