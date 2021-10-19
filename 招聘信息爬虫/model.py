from preprocessing import getXandY
import numpy as np
from sklearn.model_selection import train_test_split
from imblearn.over_sampling import RandomOverSampler
from sklearn.model_selection import GridSearchCV
from sklearn.model_selection import RandomizedSearchCV
import pickle

def save_model(filename):
    X,y = getXandY(filename+'.csv')

    X = np.array(X)
    y = np.array(y)
    X_train, X_test, y_train, y_test = train_test_split(X,y,test_size=0.2,random_state=66)
    smo = RandomOverSampler(sampling_strategy={0:95, 1:550, 2:200, 3:20},random_state=66)
    X_train, y_train = smo.fit_sample(X_train, y_train)
    from sklearn.neighbors import KNeighborsClassifier

    clf = KNeighborsClassifier(algorithm='brute',leaf_size=1,n_neighbors=9,weights='distance')
    clf.fit(X_train, y_train)
    # knn = KNeighborsClassifier()
    # #设置k的范围
    # k_range = list(range(1,10))
    # leaf_range = list(range(1,2))
    # weight_options = ['uniform','distance']
    # algorithm_options = ['auto','ball_tree','kd_tree','brute']
    # param_gridknn = dict(n_neighbors = k_range,weights = weight_options,algorithm=algorithm_options,leaf_size=leaf_range)
    # gridKNN = GridSearchCV(knn,param_gridknn,cv=10,scoring='accuracy',verbose=1)
    # gridKNN.fit(X_train, y_train)
    # print(gridKNN.best_params_)
    print('KNN')
    print(clf.score(X_test,y_test))
    with open(filename+'_knn.model', 'wb') as fw:
        pickle.dump(clf, fw)

    from sklearn.linear_model import LogisticRegression

    clf = LogisticRegression(multi_class='ovr',solver='newton-cg')
    clf.fit(X_train, y_train)
    # solver_options = ['newton-cg', 'lbfgs', 'liblinear', 'sag']
    # multi_class_options = ['ovr', 'multinomial']
    # param_grid = dict(solver = solver_options, multi_class =
    # multi_class_options)
    # grid = GridSearchCV(LogisticRegression(), param_grid, cv=12, scoring =
    # 'accuracy')
    # grid.fit(X_train, y_train)
    # print(grid.best_params_)
    print('LogisticRegression')
    print(clf.score(X_test,y_test))
    with open(filename+'_logistic.model', 'wb') as fw:
        pickle.dump(clf, fw)

    from sklearn.naive_bayes import MultinomialNB

    clf = MultinomialNB()
    clf.fit(X_train, y_train)
    print('MultinomialNB')
    print(clf.score(X_test,y_test))
    with open(filename+'_NB.model', 'wb') as fw:
        pickle.dump(clf, fw)

    from sklearn.ensemble import RandomForestClassifier

    clf = RandomForestClassifier(n_estimators=17,criterion='gini',min_samples_leaf=1)
    clf.fit(X_train, y_train)
    # parameter_space = {
    #     "n_estimators": list(range(1,20)),
    #     "criterion": ["gini", "entropy"],
    #     "min_samples_leaf": list(range(1,6)),
    # }
    # grid=GridSearchCV(RandomForestClassifier(),param_grid=parameter_space,cv=5)
    # grid.fit(X_train, y_train)
    # print(grid.best_params_)
    print('RandomForestClassifier')
    print(clf.score(X_test,y_test))
    with open(filename+'_RF.model', 'wb') as fw:
        pickle.dump(clf, fw)

    from sklearn import tree

    clf = tree.DecisionTreeClassifier()
    clf.fit(X_train, y_train)
    print('DecisionTreeClassifier')
    print(clf.score(X_test,y_test))
    with open(filename+'_DT.model', 'wb') as fw:
        pickle.dump(clf, fw)

    from sklearn.svm import SVC

    clf = SVC(kernel='rbf', probability=True)
    clf.fit(X_train, y_train)
    print('SVM')
    print(clf.score(X_test,y_test))
    with open(filename+'_SVM.model', 'wb') as fw:
        pickle.dump(clf, fw)

    from sklearn.neural_network import MLPClassifier
    mlp = MLPClassifier(hidden_layer_sizes=(200, ),activation='tanh',max_iter=1000,learning_rate_init = 0.01,solver='sgd')
    mlp.fit(X_train, y_train)
    print('MLP')
    print(mlp.score(X_test,y_test))
    with open(filename + '_NN.model', 'wb') as fw:
        pickle.dump(clf, fw)

filename = ['java','python','php']
for i in filename:
    save_model(i)