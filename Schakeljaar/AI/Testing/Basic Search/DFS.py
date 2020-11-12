Frontier = {'Fa','Fo','Go','Gr'}
Rules = [['Fa'], ['Fa','Fo'], ['Fa','Go'],['Fa','Gr'],['Fo', 'Gr']]
goal = False
path = [[],[]]
isFirst = True
i = 1
while not goal:
    i = i%2
    s = Frontier.pop()
    if isFirst:
        s2 = Frontier.pop()
    if [s,s2] in Rules or [s2, s] in Rules:
        path[i].append([s,s2])
    print(path)
    i+=1
