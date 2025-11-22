import { useContext, useEffect, useState } from 'react';
import './CategoryForm.css'
import { assets } from '../../assets/assets';
import toast from 'react-hot-toast';
import { AppContext } from "../../Context/AppContext";
import { addCategory } from "../../Service/categoryService";

const CategoryForm = () => {
    const { categories, setCategories } = useContext(AppContext)
    const [loading, setLoading] = useState(false);
    const [image, setImage] = useState(false);
    const [data, setData] = useState({
        name: "",
        description: "",
        bgColor: "#2c2c2c"
    })

    useEffect(() => {
        console.log(data);

    }, [data]);
    const onChangeHandler = (e) => {
        const { name, value } = e.target;
        setData(() => ({ ...data, [name]: value }));
    }

    const onSubmitHandler = async (e) => {
        e.preventDefault();
        setLoading(true);

        if (!image) {
            toast.error("Select image for the category");
            setLoading(false);
            return;
        }

        const formData = new FormData();

        formData.append(
            "data",
            new Blob([JSON.stringify(data)], { type: "application/json" })
        );

        formData.append("imageFile", image);

        try {
            const response = await addCategory(formData);

            if (response.status === 201) {
                setCategories([...categories, response.data]);
                toast.success("Category Added");

                setData({
                    name: "",
                    description: "",
                    bgColor: "#2c2c2c"
                });
                setImage(false);
            }
        } catch (err) {
            console.error(err);
            toast.error("Error Adding Category");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="mx-2 mt-2">
            <div className="row">
                <div className="card form-container">
                    <div className="card-body">
                        <form onSubmit={onSubmitHandler}>
                            <div className="mb-3">
                                <label htmlFor="image" className="form-label">
                                    <img src={image ? URL.createObjectURL(image) : assets.upload} alt="" width={48} />
                                </label>
                                <input type="file" name="image" id="image" className="form-control" hidden
                                    onChange={(e) => setImage(e.target.files[0])}
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="name" className="form-label">Name</label>
                                <input type="text" name="name" id="name" className="form-control" placeholder="Category Name"
                                    onChange={onChangeHandler} value={data.name}
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="description" className="form-label">Description</label>
                                <textarea name="description" id="description" className="form-control" placeholder="Write description here...."
                                    onChange={onChangeHandler} value={data.description}
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="bgColor" className="form-label">Background Color</label>
                                <br />
                                <input type="color" name="bgColor" id="bgColor" placeholder="#ffffff" style={{ width: '150px' }}
                                    onChange={onChangeHandler} value={data.bgColor}
                                />
                            </div>
                            <button type="submit" className="btn btn-primary w-100" disabled={loading}>{loading ? "Loading..." : "Submit"}</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CategoryForm;