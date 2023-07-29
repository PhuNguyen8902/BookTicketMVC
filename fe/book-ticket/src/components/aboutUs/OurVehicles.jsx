import { Box, Button, Card, CardActions, CardContent, CardMedia, Stack, Typography } from "@mui/material";
import { VehiclesAboutUsContainer, VehiclesAboutUsContent, VehiclesAboutUseUpperTitle } from "../../assets/styles/aboutUsPage";
import DriveEtaIcon from '@mui/icons-material/DriveEta';
import Img from "../../assets/imgs/th.jpg"
import "slick-carousel/slick/slick.css"; 
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";

const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 2000,
  };


export default function OurVehicles() {
    return (
        <VehiclesAboutUsContainer>
            <VehiclesAboutUsContent>
                <VehiclesAboutUseUpperTitle variant="h5">
                    <DriveEtaIcon sx={{ margin: "0 5px -4px 0" }} />
                    Our Vehicles
                </VehiclesAboutUseUpperTitle>
                <Typography variant="h4" sx={{ marginBottom: "30px"}}>The Best Vehicles For You</Typography>
                <Slider {...settings}>
                    <Card sx={{ maxWidth: 300, borderRadius: "0 0 20% 0" }}>
                        <CardMedia
                            sx={{ height: 140 }}
                            image={Img}
                            title="green iguana"
                        />
                        <CardContent>
                            <Typography gutterBottom variant="h5" component="div">
                                PPP
                            </Typography>
                            <Typography variant="body2" color="text.secondary">
                                Lizards are a widespread group of squamate reptiles, with over 6,000
                                species, ranging across all continents except Antarctica
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <Button size="small">Learn More</Button>
                        </CardActions>
                    </Card>
                    <Card sx={{ maxWidth: 300, borderRadius: "0 0 20% 0" }}>
                        <CardMedia
                            sx={{ height: 140 }}
                            image={Img}
                            title="green iguana"
                        />
                        <CardContent>
                            <Typography gutterBottom variant="h5" component="div">
                                Lizard
                            </Typography>
                            <Typography variant="body2" color="text.secondary">
                                Lizards are a widespread group of squamate reptiles, with over 6,000
                                species, ranging across all continents except Antarctica
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <Button size="small">Learn More</Button>
                        </CardActions>
                    </Card>
                    <Card sx={{ maxWidth: 300, borderRadius: "0 0 20% 0" }}>
                        <CardMedia
                            sx={{ height: 140 }}
                            image={Img}
                            title="green iguana"
                        />
                        <CardContent>
                            <Typography gutterBottom variant="h5" component="div">
                                Lizard
                            </Typography>
                            <Typography variant="body2" color="text.secondary">
                                Lizards are a widespread group of squamate reptiles, with over 6,000
                                species, ranging across all continents except Antarctica
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <Button size="small">Learn More</Button>
                        </CardActions>
                    </Card>
                    <Card sx={{ maxWidth: 300, borderRadius: "0 0 20% 0" }}>
                        <CardMedia
                            sx={{ height: 140 }}
                            image={Img}
                            title="green iguana"
                        />
                        <CardContent>
                            <Typography gutterBottom variant="h5" component="div">
                                Lizard
                            </Typography>
                            <Typography variant="body2" color="text.secondary">
                                Lizards are a widespread group of squamate reptiles, with over 6,000
                                species, ranging across all continents except Antarctica
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <Button size="small">Learn More</Button>
                        </CardActions>
                    </Card>
                </Slider>
            </VehiclesAboutUsContent>
        </VehiclesAboutUsContainer>
    )
}