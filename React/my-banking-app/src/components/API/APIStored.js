import axios from "axios";

const Rest_api = "http://localhost:8080/api/users";
const APIStored = () => axios.get(Rest_api);

export default APIStored;
