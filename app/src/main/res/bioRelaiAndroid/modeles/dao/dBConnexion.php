<?php
require_once 'configs/param.php';



class DBConnex extends PDO
{
    
    private $_db;
    private static $instance;
    
    public static function getInstance(){
        if ( !self::$instance ){
            self::$instance = new DBConnex();
        }
        return self::$instance;
    }
    function __construct(){
        try {
            parent::__construct(Param::$dsn ,Param::$user, Param::$pass);
        } catch (Exception $e) {
            echo $e->getMessage();
            die("Impossible de se connecter. " );
        }
    }
    public function queryFetchAll($sql){
        $sth  = $this -> query($sql);
        
        if ( $sth ){
            return $sth -> fetchAll(PDO::FETCH_ASSOC);
        }
        
        return false;
    }
    
    public function queryFetchFirstRow($sql){
        $sth    = $this -> query($sql);
        $result    = array();
        
        if ( $sth ){
            $result  = $sth -> fetch();
        }
        
        return $result;
    }
    
    public function getDb()
    {
        if(empty($this->_db))
        {
            $this->_db = new PDO('mysql:host='.$GLOBALS["DbHost"].';dbname='.$GLOBALS["DbName"].';charset=utf8',
                $GLOBALS["DbUser"],
                $GLOBALS["DbPass"]);
            $this->_db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        }
        return $this->_db;
    }
    
    
    //charger utilisateur
    
    public function verification($login, $password)
    {
        $requete = DBConnex::getInstance()->prepare("SELECT statut, mail, nomutilisateur, prenomutilisateur from UTILISATEUR WHERE
		LOGIN = :login
		AND MDP = :password");
        $requete->execute(array(
            ':login' => $login,
            ':password' => $password));
        
        $result = $requete->fetch(PDO::FETCH_ASSOC); 
		
		return $result;
            
    }
}


