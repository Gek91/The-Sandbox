from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker


#We are using SQLLite as a DB
#SQLalchemy is an ORM

SQLALCHEMY_DATABASE_URL = "sqlite:///./sql/sql_app.db"
# SQLALCHEMY_DATABASE_URL = "postgresql://user:password@postgresserver/db"

#Check_same_thread needed for SqlLite because by default SQLLite allow only one thread to communicate with it
#Assuming that each thread woluld handle an indipendent request this provent accidentally sharing the same connection for different requests
#But in fast api more than one thread could interact with the database for the same request, so we need to make SQLite allow it
engine = create_engine(
    SQLALCHEMY_DATABASE_URL, connect_args={"check_same_thread": False} 
)

#Database session
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

#ORM Model super class
Base = declarative_base()
