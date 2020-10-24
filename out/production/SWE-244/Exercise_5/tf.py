import re, sys, collections
import os

from concurrent.futures import ThreadPoolExecutor, wait, ALL_COMPLETED, FIRST_COMPLETED
import time


time_start=time.time()


word_sum=[]

def countWord(filename):
    global word_sum
    print("reading file "+filename)
    words = re.findall('\w{3,}', open(filename).read().lower())
    word_sum += [w for w in words if w not in stopwords]

    
rootdir=os.path.join(r'E:\OneDrive - personalmicrosoftsoftware.uci.edu\UCI algo\SWE-244\src\Exercise_5')
pool = ThreadPoolExecutor(max_workers=4)
files=os.listdir(rootdir)
stopwords = set(open('stop_words').read().split(','))
for (dirpath,dirnames,filenames) in os.walk(rootdir):
    all_task=[]
    for filename in filenames:
        if os.path.splitext(filename)[1]=='.txt':
            
            all_task.append(pool.submit(countWord, filename))
            
wait(all_task, return_when=ALL_COMPLETED)

#print(all_task)
pool.shutdown(wait=True)

counts = collections.Counter(word_sum)


for (w, c) in counts.most_common(40):
    
    print (w, '-', c)


time_end=time.time()

print('totally time cost',time_end-time_start)
