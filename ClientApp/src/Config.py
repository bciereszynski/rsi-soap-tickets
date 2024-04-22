import os
from dotenv import load_dotenv

class Config:
    load_dotenv()

    server = os.getenv('SERVER')
