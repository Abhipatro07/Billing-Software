import Category from '../Category/Category';
import './DisplayCategory.css'

export const DisplayCategory = ({categories , setSelectedCategory , selectedCategory}) => {
    return(
        <div className="row g-3" style={{width:'100%' , margin:0}}>
            {categories.map(category => (
                <div key={category.categoryId} className='col-md-3 col-sm-6' style={{padding:'0 10px'}}>
                    <Category 
                        categoryName = {category.name}
                        imageFile = {`http://localhost:8080${category.imageUrl}`}
                        numberOfItems = {category.items}
                        bgColor = {category.bgColor}
                        isSelected = {selectedCategory === category.categoryId}
                        onClick = {() => setSelectedCategory(category.categoryId)}

                    
                    />
                </div>
            ))}
        </div>
    )
}

export default DisplayCategory;