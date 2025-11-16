import axios from "axios";

export const addCategory = async (category) => {
    return await axios.post('https://localahost:8080/categories' , category)
}

export const deleteCategory = async (categoryId) => {
    return await axios.delete(`https://localhost:8080/categories/${categoryId}`)
}

export const fetchCategories = async () => {
    return await axios.get('https://localhost:8080/categories')
}