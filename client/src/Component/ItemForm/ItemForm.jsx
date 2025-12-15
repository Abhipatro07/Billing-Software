import { useContext, useState } from 'react';
import {AppContext} from '../../Context/AppContext'
import {assets} from '../../assets/assets'
import {addItem} from '../../Service/ItemService'
import './ItemForm.css'
import toast from 'react-hot-toast';


const ItemForm = () => {
    const {categories , setItemsData , itemsData , setCategories} = useContext(AppContext)
    const [image , setImage] = useState(false)
    const [loading , setLoading] = useState(false)
    const [data , setData] = useState({
        name:"",
        description:"",
        categoryId:"",
        price:""
    })

    const onChangeHandler = (e) => {
        const { name, value } = e.target;
        setData((data) => ({ ...data, [name]: value }));
    }

    const onSubmitHandler = async (e) => {
        e.preventDefault();
        setLoading(true)
        const formData = new FormData();
        formData.append("item" , JSON.stringify(data))
        formData.append("imageFile" , image)
        try{
            if(!image){
                toast.error("Select Image")
                return;
            }
            const response = await addItem(formData)
            if(response.status === 201){
                setItemsData([...itemsData , response.data])
                setCategories((prevCategories) => 
                    prevCategories.map((category) => category.categoryId === data.categoryId ? 
                        {...category , items:category.items + 1} : category))
                toast.success("Item Added")
                setData({
                    name:"",
                    description:"",
                    price:"",
                    categoryId:""
                })
                setImage(false)
            }
            else{
                toast.error("Unable to add item")
            }
        }
        catch(error){
            console.error(error);
            toast.error("Unable to add new Item")            
        }
        finally{
            setLoading(false)
        }
    }
    return(
       <div className="item-form-container" style={{height:'100vh' , overflowY:"auto" , overflowX:"hidden"}}>
         <div className="mx-2 mt-2">
            <div className="row">
                <div className="card form-container">
                    <div className="card-body">
                        <form onSubmit={onSubmitHandler}>
                            <div className="mb-3">
                                <label htmlFor="image" className="form-label">
                                    <img src={image ? URL.createObjectURL(image) : assets.upload} alt="" width={48}/>
                                </label>
                                <input type="file" name="image" id="image" className="form-control" hidden 
                                    onChange={(e) => setImage(e.target.files[0])}/>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="name" className="form-label">Name</label>
                                <input type="text" name="name" id="name" className="form-control" placeholder="Item Name"
                                    onChange={onChangeHandler} value={data.name}/>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="category" className="form-label">Category</label>
                                <select name="categoryId" id="category" className="form-control" onChange={onChangeHandler} value={data.categoryId}>
                                    <option value="Select Category">--SELECT CATEGORY--</option>
                                    {categories.map((category , index) => (
                                        <option key={index} value={category.categoryId}>{category.name}</option>
                                    ))}
                                </select>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="price" className="form-label">Price</label>
                                <input type="number" name="price" id="price" className="form-control" placeholder="&#8377;200.00"
                                    onChange={onChangeHandler} value={data.price}/>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="description" className="form-label">Description</label>
                                <textarea name="description" id="description" className="form-control" placeholder="Write Descrption here...."
                                    onChange={onChangeHandler} value={data.description}/>
                            </div>
                            
                            <button type="submit" className="btn btn-primary w-100" disabled={loading}>
                                {loading ? "Loading..." : "save"}
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
       </div>
    )
}

export default ItemForm;