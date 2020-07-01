%% Initialization
clear ; close all; clc

fprintf('Loading and Visualizing Data ...\n')

load('ex6data3.mat');

fprintf("绘制 X,y\n");
plotData(X, y);
fprintf('Program paused. Press enter to continue.\n');
pause;

fprintf("绘制 Xval,yval\n");
plotData(Xval, yval);
fprintf('Program paused. Press enter to continue.\n');
pause;

cycleNo = 20
step = 0.0001;
gap = step * (cycleNo / 2);

C = 1;
sigma = 1;

midC = 0.002;
midSigma = 0.064;
initC = midC - gap;
initSigma = midSigma -gap;
fprintf("C: [%f, %f] mid: %f\n", midC - gap, midC + gap, midC);
fprintf("Sigma: [%f, %f] mid: %f\n", midSigma - gap, midSigma + gap, midSigma);
fprintf('Program paused. Press enter to continue.\n');

initC = 0.001;
initSigma = 0.001;

bestPre = -1;
pause;

for i=1:cycleNo,
  initSigma = 0.001;
  % initSigma = midSigma - gap;
  for j=1:cycleNo,
      model = svmTrain(X, y, initC, @(x1, x2)gaussianKernel(x1, x2, initSigma));
      predictions = svmPredict(model, Xval);
      preError = mean(double(predictions ~= yval));
      if bestPre < 0 || preError < bestPre,
          C = initC;
          sigma = initSigma;
          bestPre = preError;
      end
      initSigma *= 1.5;
     end
  initC *= 1.5;
end

disp("Result: ");
disp(C);
disp(sigma);
% fprintf("C: [%f, %f] mid: %f\n", midC - gap, midC + gap, midC);
% fprintf("Sigma: [%f, %f] mid: %f\n", midSigma - gap, midSigma + gap, midSigma);
% fprintf('Program paused. Press enter to continue.\n');

model= svmTrain(X, y, C, @(x1, x2) gaussianKernel(x1, x2, sigma));
visualizeBoundary(X, y, model);

fprintf('Program paused. Press enter to continue.\n');
pause;
