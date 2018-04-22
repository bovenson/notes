function [c, ceq] = A(x)
n = 3;
m = 2;
disp(m);
for k=1:m
    for i=1:n+1
        for j=1:n+1
            L = (k-1) * (n+1) * (n+1) + (i-1) * (n+1) + j;
            disp([num2str(i) ' ' num2str(j) ' ' num2str(k)]);
            if i == j && x(L) != 0
                ceq(1) = 1;
                c(1) = [];
            end
        end
    end
end
