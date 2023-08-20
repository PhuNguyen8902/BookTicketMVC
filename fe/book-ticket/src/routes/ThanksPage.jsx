import { useEffect } from "react";
import momoService from "../services/momoService";
import { SERVER } from "../assets/js/constants";
import { useState } from "react";
import { useSelector } from "react-redux";

export default function ThanksPage() {
  const [mess, setMess] = useState("Thanks to dat ve");

  const fetchData = async () => {
    const momoDataStr = localStorage.getItem("momo");
    const momoData = JSON.parse(momoDataStr);
    const rs = await momoService.postGetMess(momoData);
    setMess(rs.message);
  };
  useEffect(() => {
    fetchData();
    localStorage.removeItem("momo");
  }, []);
  return (
    <>
      <h1>{mess}</h1>
    </>
  );
}
