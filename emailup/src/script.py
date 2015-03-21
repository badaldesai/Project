import psycopg2
import sys

def define_DB():
    
    try:
        conn = psycopg2.connect("dbname='db1' host = 'localhost' '' ''")
    except:
        print("I am unable to connect to the database")
        
    cur = conn.cursor()
    
    from collections import Counter
    a = []
    for k,v in lst.items():
        a=v