import { useContext, useState } from "react";
import { AppContext } from "../../Context/AppContext";
import './CategoryList.css'

const CategoryList = () => {
    const {categories} = useContext(AppContext)
    const [searchTerm , setSearchTerm] = useState(' ')
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
                {categories.map((category , index) => (
                    <div key = {index} className="col-12">
                        <div className="card p-3" style={{backgroundColor:category.bgColor}}>
                        <div className="d-flex align-items-center">
                            <div style={{marginRight:"15px"}}>
                                <img src= {`http://localhost:8080${category.imageUrl}`} alt={category.name} className="category-image" />
                            </div>
                            <div className="flex-grow-1">
                                <h5 className="mb-1 text-white">{category.name}</h5>
                                <p className="mb-0 text-white">{category.items} Items</p>
                            </div>
                            <div>
                                <button className="btn btn-danger btn-sm">
                                    <i className="bi bi-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default CategoryList;