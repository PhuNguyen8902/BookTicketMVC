import { useEffect } from "react";
import momoService from "../services/momoService";
import { SERVER } from "../assets/js/constants";
import { useState } from "react";
import { useSelector } from "react-redux";
import { Container } from "@mui/material";
import { TicketContainer } from "../assets/styles/homePage";
import ticketService from "../services/ticketService";

export default function ThanksPage() {
  // const ticket = useSelector((state) => {
  //   return state;
  // });
  // console.log(ticket);

  const [mess, setMess] = useState("Thanks to dat ve");

  const addTicketMomo = async (result, tic) => {
    console.log(tic);
    const rs = await ticketService.addTicket(tic);
    console.log(rs);
    if (rs) {
      setMess(result.message);
    } else {
      setMess("Failed booking");
    }
  };

  const fetchData = async () => {
    const momoDataStr = localStorage.getItem("momo");
    const momoData = JSON.parse(momoDataStr);
    const ticketDataStr = localStorage.getItem("ticket");
    const ticketData = JSON.parse(ticketDataStr);
    console.log(ticketData);

    const rs = await momoService.postGetMess(momoData);
    console.log(rs);
    if (rs.resultCode == 0) {
      addTicketMomo(rs, ticketData);
    } else {
      setMess(rs.message);
    }
  };
  useEffect(() => {
    fetchData();
    localStorage.removeItem("momo");
    localStorage.removeItem("ticket");
  }, []);
  return (
    <>
      <Container className="thanks__container">
        <h1>{mess}</h1>
      </Container>
    </>
  );
}
