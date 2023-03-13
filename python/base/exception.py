
def main():
   
    try: len(s) #s variable not existing
    except Exception: print('error')
    else: print('no error')
    finally: print('alwyas executed')
 
if __name__ == '__main__':
    main()