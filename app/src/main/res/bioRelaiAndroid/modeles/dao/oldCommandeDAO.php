<?php
require_once 'dBConnexion.php';

class commandeDAO
{  
    public function getAllOrders($idAdherent = null, $dailyOnly = null,$producteur = null)
    {
		if
		$sql = "SELECT * FROM COMMANDE";
		if($idAdherent != null)
			$sql .= " WHERE idAdherent = ".$idAdherent;
		
		if($dailyOnly != null)
		{
			if($idAdherent == null)
				$sql .= " WHERE ";
			else
				$sql .= " AND ";
			
			$sql .= "DATECOMMANDE = '".date("Y-m-d")."'";
		}
        $requete = DBConnex::getInstance()->prepare($sql);
        $requete->execute();
        $result = $requete->fetchAll(PDO::FETCH_ASSOC); 
		
		return $result;
            
    }
}


