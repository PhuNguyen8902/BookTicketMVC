import { useEffect } from "react";
import momoService from "../services/momoService";
import { SERVER } from "../assets/js/constants";
import { useState } from "react";
import { useSelector } from "react-redux";

export default function ThanksPage() {
  const [mess, setMess] = useState("Thanks to dat ve");

  const user = useSelector((state) => state.auth.user);
  const fetchData = async () => {
    const momoDataStr = localStorage.getItem("momo");
    const momoData = JSON.parse(momoDataStr);
    momoData.user_id = user.id;

    const rs = await momoService.postGetMess(momoData);
    setMess(rs.message);
  };
  useEffect(() => {
    if (user.id != undefined) {
      fetchData();
      // localStorage.removeItem("momo");
    }
  }, [user]);
  return (
    <>
      <h1>{mess}</h1>
    </>
  );
}
