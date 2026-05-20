package project.ptit_cntt1_it211_session06.dto.response;

public class CourseResponseDTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    public CourseResponseDTO() {
    }

    public CourseResponseDTO(
            Long id,
            String name,
            String description,
            Double price,
            String imageUrl
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
