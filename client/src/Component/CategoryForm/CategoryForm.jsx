import './CategoryForm.css'

const CategoryForm = () => {
    return (
        <div className="mx-2 mt-2">
            <div className="row">
                <div className="card form-container">
                    <div className="card-body">
                        <form>
                            <div className="mb-3">
                                <label htmlFor="image" className="form-label">
                                    <img src="https://placehold.co/48x48" alt="" width={48}/>
                                </label>
                                <input type="file" name="image" id="image" className="form-control" hidden />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="name" className="form-label">Name</label>
                                <input type="text" name="name" id="name" className="form-control" placeholder="Category Name"/>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="descrption" className="form-label">Description</label>
                                <textarea name="descrption" id="descrption" className="form-control" placeholder="Write Descrption here...."/>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="bgColor" className="form-label">Background Color</label>
                                <br />
                                <input type="color" name="bgColor" id="bgColor" placeholder="#ffffff" style={{width:'150px'}}/>
                            </div>
                            <button type="submit" className="btn btn-primary w-100">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CategoryForm;