import { useContext, useState } from "react";
import { AppContext } from "../../Context/AppContext";
import { deleteItem } from "../../Service/ItemService";
import toast from "react-hot-toast";
import './ItemList.css'

const ItemList = () => {
    const {itemsData , setItemsData} = useContext(AppContext)
    const [searchTerm , setSearchTerm] = useState('')
    const filteredItems = itemsData.filter((item) => {
        return item.name.toLowerCase().includes(searchTerm.toLowerCase())
    })

    const removeItem = async (itemId) => {
        try{
            const response = await deleteItem(itemId)
            if(response.status === 204){
                const updatedItem = itemsData.filter(item=> item.itemId !== itemId)
                setItemsData(updatedItem)
                toast.success("Item Deleted")
            }
            else{
                toast.error("unable to delete the item")
            }
        }
        catch(error){
            console.error(error);
            toast.error("Unable to delete the item")
            
        }
    }
    return(
        <div className="category-list-container" style={{height:"100vh" , overflowY:"auto" , overflowX:"hidden"}}>
            <div className="row pe-2">
                <div className="input-group mb-3">
                    <input type="text" name = "keyword" id="keyword" className="form-control" placeholder="Search by keyword"
                            onChange={(e) => setSearchTerm(e.target.value)} value={searchTerm}
                    />

                    <span className="input-group-text bg-warning">
                        <i className="bi bi-search"></i>
                    </span>

                </div>
            </div>
            <div className="row g-2 pe-3">
                {filteredItems.map((item , index) => (
    <div className="col-12" key={index}>
        <div className="card p-3 bg-dark">
            <div className="d-flex align-items-center">

                <div className="item-image">
                    <img src={`http://localhost:8080${item.imageUrl}`} alt={item.name} />
                </div>

                <div className="flex-grow-1">
                    <h6 className="mb-1 text-white">{item.name}</h6>
                    <p className="mb-0 text-white">Category: {item.categoryName}</p>

                    <span className="badge rounded-pill text-bg-warning">
                        ₹{item.price}
                    </span>
                </div>

                <button
                    className="btn btn-danger btn-sm"
                    onClick={() => removeItem(item.itemId)}
                >
                    <i className="bi bi-trash"></i>
                </button>
            </div>
        </div>
    </div>
))}

            </div>
        </div>
    )
}

export default ItemList;